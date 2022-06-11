package com.nicolas.nfchcejava.nfc.se.states;

import com.nicolas.nfchcejava.nfc.se.Command;

public abstract class State {
    public abstract CommandResult processCommand(Command c);
}
