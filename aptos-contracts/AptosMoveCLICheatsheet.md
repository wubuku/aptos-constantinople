# Aptos Move CLI Cheatsheet

[ToC]

## Monster aggregate

### Create method

```shell
aptos move run --function-id 'default::monster_aggregate::create' \
--args address:monster_id u64:monster_type \
--assume-yes
```

## Map singleton object

