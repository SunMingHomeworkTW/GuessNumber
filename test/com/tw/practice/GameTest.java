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
    public void shouldWinAtFirst() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");
        Mockito.when(mockedReader.read()).thenReturn("1234");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedReader, Mockito.times(1)).read();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("you win");
        Mockito.verify(mockedPrinter, Mockito.times(0)).print("Game Over");
    }

    @Test
    public void shouldWinAtSecond() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");

//        Mockito.doReturn("1243").doReturn("1234").doReturn("3456").when(mockedReader.read());
        Mockito.when(mockedReader.read()).thenReturn("1243").thenReturn("1234").thenReturn("3456");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("you win");
        Mockito.verify(mockedReader, Mockito.times(2)).read();
        Mockito.verify(mockedPrinter, Mockito.times(0)).print("Game Over");
    }

    @Test
    public void shouldWinAtSixth() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");

        Mockito.when(mockedReader.read()).thenReturn("1243").thenReturn("3456").thenReturn("7890")
                .thenReturn("4567").thenReturn("6543").thenReturn("1234").thenReturn("3456");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("you win");
        Mockito.verify(mockedReader, Mockito.times(6)).read();
        Mockito.verify(mockedPrinter, Mockito.times(0)).print("Game Over");
    }

    @Test
    public void shouldOver() throws Exception {
        Mockito.when(mockedGenerator.generate()).thenReturn("1234");

        Mockito.when(mockedReader.read()).thenReturn("1243").thenReturn("3456").thenReturn("7890")
                .thenReturn("4567").thenReturn("6543").thenReturn("3456");

        Game game = new Game(mockedGenerator, mockedPrinter, mockedReader);

        game.start();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("please input a 4 digit number:");
        Mockito.verify(mockedPrinter, Mockito.times(0)).print("you win");
        Mockito.verify(mockedReader, Mockito.times(6)).read();
        Mockito.verify(mockedPrinter, Mockito.times(1)).print("Game Over");
    }
}