package com.helper.work.operator;

import com.helper.work.entity.Phone;
import com.helper.work.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    @Test
    void list_empty() {
        final List<Phone> list = phoneService.list();
        assertTrue(list.size() == 0, "Phone listAll is empty.");
    }

    @Test
    void list_filled() {
        when(phoneRepository.findAll()).thenReturn(List.of(Phone.builder().build()));
        final List<Phone> list = phoneService.list();
        assertEquals(list.size(), 1);
    }

}