package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/8/11.
 */

public class AddressInfo {

    private String receiver;
    private String receiverNumber;
    private String receiverAddress;

    public AddressInfo(String receiver, String receiverNumber, String receiverAddress) {
        this.receiver = receiver;
        this.receiverNumber = receiverNumber;
        this.receiverAddress = receiverAddress;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "receiver='" + receiver + '\'' +
                ", receiverNumber='" + receiverNumber + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                '}';
    }
}
