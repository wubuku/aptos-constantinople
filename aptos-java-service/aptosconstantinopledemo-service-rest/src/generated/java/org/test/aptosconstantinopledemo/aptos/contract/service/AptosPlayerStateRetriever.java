// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;


import com.github.wubuku.aptos.bean.AccountResource;
import com.github.wubuku.aptos.utils.*;
import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;
import org.test.aptosconstantinopledemo.aptos.contract.ContractConstants;
import org.test.aptosconstantinopledemo.aptos.contract.DomainBeanUtils;
import org.test.aptosconstantinopledemo.aptos.contract.repository.AptosAccountRepository;
import org.test.aptosconstantinopledemo.domain.player.*;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.aptos.contract.Player;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosPlayerStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, PlayerState.MutablePlayerState> playerStateFactory;


    public AptosPlayerStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, PlayerState.MutablePlayerState> playerStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.playerStateFactory = playerStateFactory;
    }

    public PlayerState retrievePlayerState(String playerId) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<Player.Tables> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.PLAYER_MODULE_TABLES,
                    Player.Tables.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String tableHandle = accountResource.getData().getPlayerTable().getHandle();
        Player player;
        try {
            player = aptosNodeApiClient.getTableItem(
                    tableHandle,
                    ContractConstants.toNumericalAddressType(ContractConstants.PLAYER_ID_TYPE, this.aptosContractAddress),
                    this.aptosContractAddress + "::" + ContractConstants.PLAYER_MODULE_PLAYER,
                    playerId,
                    Player.class,
                    null
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toPlayerState(player);
    }

    private PlayerState toPlayerState(Player player) {
        PlayerState.MutablePlayerState playerState = playerStateFactory.apply(player.getPlayerId());
        playerState.setVersion(player.getVersion());
        playerState.setValue(player.getValue());
        return playerState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}

