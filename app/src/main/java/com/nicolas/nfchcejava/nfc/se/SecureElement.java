package com.nicolas.nfchcejava.nfc.se;

import com.nicolas.nfchcejava.log.Log;
import com.nicolas.nfchcejava.nfc.se.states.CommandResult;
import com.nicolas.nfchcejava.nfc.se.states.InitialState;
import com.nicolas.nfchcejava.nfc.se.states.State;

public class SecureElement {
    private static final String TAG = "SoftwareSEWrapper";
    private State state;

    public SecureElement(Application[] applications) {
        this.state = new InitialState(applications);
    }

    byte[] processCommand(Command command) {
        Log.i(TAG, "processCommand()");

        CommandResult result = this.state.processCommand(command);
        this.state = result.getState();
        return result.getResponse();
    }
}
