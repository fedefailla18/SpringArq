package operator

import com.helper.work.entity.Phone
import com.helper.work.operator.PhoneService
import com.helper.work.repository.PhoneRepository
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.mockito.Mockito.when

class PhoneServiceSpec extends Specification {

    def phoneRepository = Mock(PhoneRepository)

    def phoneService = Mock(PhoneService)

    def setup() {
        println "seting up phone service"
    }
    def "list_empty"() {
        final List<Phone> list = phoneService.list()
        assertTrue(list.size() == 0, "Phone listAll is empty.")
    }

    def "list_filled"() {
        when(phoneRepository.findAll()).thenReturn(List.of(Phone.builder().build()))
        final List<Phone> list = phoneService.list()
        assertEquals(list.size(), 1)
    }
}
