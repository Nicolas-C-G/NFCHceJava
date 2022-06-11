package com.nicolas.nfchcejava.application;

import com.nicolas.nfchcejava.nfc.desfire.AID;
import com.nicolas.nfchcejava.nfc.desfire.InvalidParameterException;

class LibraryAID extends AID {
    LibraryAID() throws InvalidParameterException {
        super("015548");
    }
}

