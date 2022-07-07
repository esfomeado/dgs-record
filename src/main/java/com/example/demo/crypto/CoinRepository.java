package com.example.demo.crypto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CoinRepository
{
    private List<Coin> coins;

    public List<Coin> findAll()
    {
        return coins;
    }

    public Coin findByName( final String name )
    {
        return coins.stream()
                    .filter( coin -> name.equalsIgnoreCase( coin.name() ) )
                    .findFirst()
                    .orElse( null );
    }

    public Coin save( final CoinInput coinInput )
    {
        final Coin coin = new Coin( coinInput.name(), RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) );

        coins.add( coin );

        return coin;
    }

    @PostConstruct
    private void init()
    {
        coins = generateCoins();
    }

    public List<Coin> generateCoins()
    {
        final List<Coin> coinList = new ArrayList<>();
        coinList.add( new Coin( "BTC", RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) ) );
        coinList.add( new Coin( "ETH", RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) ) );
        coinList.add( new Coin( "XMR", RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) ) );
        coinList.add( new Coin( "ADA", RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) ) );
        coinList.add( new Coin( "DOGE", RandomUtils.nextInt(), LocalDateTime.now().truncatedTo( ChronoUnit.SECONDS ) ) );

        return coinList;
    }
}
