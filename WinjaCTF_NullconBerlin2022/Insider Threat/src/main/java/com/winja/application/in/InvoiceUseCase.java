package com.winja.application.in;

import java.io.IOException;


public interface InvoiceUseCase {

    void generateInvoice(String avatarUrl) throws IOException;

}
