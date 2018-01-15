package com.tw.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private NumberGenerator mockedGenerator;

    @Mock
    private Printer mockedPrinter;

    @Mock
    private NumberReader mockedReader;

    @Test
    public void shouldReturn4A0BWhenInputSameNumber() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");
        Mockito.when(mockedReader.read()).thenReturn("1234");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("you win");
    }

    @Test
    public void shouldReturn0A0BWhenInputDifferentNumber() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");
        Mockito.when(mockedReader.read()).thenReturn("5678");
        Mockito.when(mockedReader.read()).thenReturn("1256");
        Mockito.when(mockedReader.read()).thenReturn("4728");
        Mockito.when(mockedReader.read()).thenReturn("4567");
        Mockito.when(mockedReader.read()).thenReturn("1357");
        Mockito.when(mockedReader.read()).thenReturn("1258");
        Mockito.when(mockedReader.read()).thenReturn("1890");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedPrinter, Mockito.times(0)).print("you win");
        Mockito.verify(mockedReader, Mockito.times(6)).read();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("Game Over");
    }

}