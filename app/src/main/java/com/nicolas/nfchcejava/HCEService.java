package com.nicolas.nfchcejava;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import com.nicolas.nfchcejava.application.LibraryApplication;
import com.nicolas.nfchcejava.log.Log;
import com.nicolas.nfchcejava.nfc.ByteUtils;
import com.nicolas.nfchcejava.nfc.Iso7816;
import com.nicolas.nfchcejava.nfc.desfire.InvalidParameterException;
import com.nicolas.nfchcejava.nfc.se.Application;
import com.nicolas.nfchcejava.nfc.se.Emulation;
import com.nicolas.nfchcejava.nfc.se.SecureElement;

public class HCEService extends HostApduService {
    private static final String TAG = "HCEService";
    private static boolean firstInteraction = true;
    private static Emulation emulation;
    private final NotificationService notifications = new NotificationService(this);

    @Override
    public byte[] processCommandApdu(byte[] command, Bundle extras) {
        byte[] response = firstInteraction ? getFirstResponse(command) : getNextResponse(command);
        Log.i(TAG, "--> " + ByteUtils.toHexString(response));
        return response;
    }

    private byte[] getFirstResponse(byte[] command) {
        Log.reset(TAG, "<-- " + ByteUtils.toHexString(command));
        notifications.createNotificationChannel(this);
        notifications.show("<--" + ByteUtils.toHexString(command));

        try {
            emulation = getEmulation();
            firstInteraction = false;
            return Iso7816.RESPONSE_SUCCESS;
        } catch (InvalidParameterException e) {
            return Iso7816.RESPONSE_INTERNAL_ERROR;
        }
    }

    private Emulation getEmulation() throws InvalidParameterException {
        Application libraryApplication = new LibraryApplication();
        Application[] applications = new Application[] {libraryApplication};
        SecureElement seWrapper = new SecureElement(applications);
        return new Emulation(seWrapper);
    }

    private byte[] getNextResponse(byte[] command) {
        Log.i(TAG, "<-- " + ByteUtils.toHexString(command));
        notifications.show("<--" + ByteUtils.toHexString(command));
        return emulation.getResponse(command);
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i(TAG, "onDeactivated(). Reason: " + reason);
        firstInteraction = true;
    }

}
