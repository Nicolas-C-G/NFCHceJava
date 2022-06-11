package com.nicolas.nfchcejava.application;

import com.nicolas.nfchcejava.nfc.desfire.InvalidParameterException;
import com.nicolas.nfchcejava.nfc.se.Application;

public class LibraryApplication extends Application {
    public LibraryApplication() throws InvalidParameterException {
        super(new LibraryAID(), new LibraryAESKey0(), new LibraryFile0());
    }
}

