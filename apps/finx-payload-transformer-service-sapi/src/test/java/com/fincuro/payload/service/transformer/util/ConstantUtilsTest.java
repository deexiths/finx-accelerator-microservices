package com.fincuro.payload.service.transformer.util;

import com.fincuro.payload.service.util.ConstantUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
 class ConstantUtilsTest {

    @InjectMocks
    ConstantUtils constantUtils;

    @Test
    void testUtils(){
     assertEquals("Error in DB operation. error:",ConstantUtils.DB_ERROR_MESSAGE);
     assertEquals("Configuration not found",ConstantUtils.CONFIG_NOT_FOUND_MESSAGE);
     assertEquals("Error:{}",ConstantUtils.ERROR);
    }


}
