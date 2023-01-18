package operator;

import com.helper.work.entity.Users;
import com.helper.work.operator.UsersService;
import com.helper.work.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository repository;
    @InjectMocks
    private UsersService service;

    @Test
    void getUsers() {
        when(repository.findAll()).thenReturn(List.of(Users.builder().build()));
        final List<Users> users = service.getUsers();
        assertEquals(users.size(),1);
    }

    @Test
    void saveUser() {
        final Users user = Users.builder().id(1L).firstName("fede").build();
        when(repository.save(any())).thenReturn(user);
        final Users savedUser = service.saveUser(user);
        assertEquals(savedUser, user);
    }
}