Feature: Test all store features?
    Should test all basic features of store

    Scenario:Should insert store when is new
        Given:Store is new
        And:The store doesn't exists in database
        Then:Insert the <store>
