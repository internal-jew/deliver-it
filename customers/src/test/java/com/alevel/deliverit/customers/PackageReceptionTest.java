package com.alevel.deliverit.customers;

import com.alevel.deliverit.Package;
import com.alevel.deliverit.PackageReceipt;
import com.alevel.deliverit.PostalAddress;
import com.alevel.deliverit.Sender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("PackageReception should")
class PackageReceptionTest {

    @Test
    @DisplayName("Receiving a package from the sender")
    void accept() {
        Package aPackage = getPackage();
        PostalAddress postalAddress = getPostalAddress();
        Sender sender = getSender();

        final PackageReception packageReception = PackageReception
                .builder()
                .setPackage(aPackage)
                .setPostalAddress(postalAddress)
                .setSender(sender)
                .build();

        PackageReceipt packageReceipt = packageReception.accept();
    }

    private Package getPackage(){
        return mock(Package.class);
    }

    private Sender getSender(){
        return mock(Sender.class);
    }

    private PostalAddress getPostalAddress(){
        return mock(PostalAddress.class);
    }
}