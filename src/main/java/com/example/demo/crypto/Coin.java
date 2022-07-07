package com.example.demo.crypto;

import java.time.LocalDateTime;

public record Coin( String name, int price, LocalDateTime time )
{
}
