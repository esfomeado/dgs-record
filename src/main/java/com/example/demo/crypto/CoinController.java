package com.example.demo.crypto;

import java.time.Duration;
import java.util.List;

import org.reactivestreams.Publisher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class CoinController
{
    private final CoinRepository coinRepository;

    @DgsQuery
    public List<Coin> coins()
    {
        return coinRepository.findAll();
    }

    @DgsQuery
    public Coin coin( @InputArgument String name )
    {
        return coinRepository.findByName( name );
    }

    @DgsMutation
    public Coin createCoin( @InputArgument CoinInput input )
    {
        return coinRepository.save( input );
    }

    @DgsSubscription
    public Publisher<List<Coin>> liveCoinsPrice()
    {
        return Flux.interval( Duration.ofSeconds( 3 ) )
                   .map( timer -> coinRepository.generateCoins() );
    }
}
