// // <autogenerated>
// //   This file was generated by dddappp code generator.
// //   Any changes made to this file manually will be lost next time the file is regenerated.
// // </autogenerated>
//
// module aptos_constantinople_demo_store::aptos_constantinople_demo_store_init {
//     use aptos_constantinople_demo::aptos_constantinople_demo_init;
//     use aptos_constantinople_demo_map::aptos_constantinople_demo_map_init;
//     use aptos_constantinople_demo_store::genesis_account;
//
//     public entry fun initialize(account: &signer) {
//         genesis_account::initialize(account);
//         let store_account = genesis_account::resource_account_signer_for_genesis_account(account);
//         aptos_constantinople_demo_init::initialize(&store_account);
//         aptos_constantinople_demo_map_init::initialize(&store_account);
//     }
//
// }
