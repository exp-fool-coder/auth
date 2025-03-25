package com.expfool.leaderboard.auth.mapper;

import com.expfool.leaderboard.auth.domain.RegistrationRequest;
import com.expfool.libs.testlib.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class UserRepresentationMapperTest {

    @Autowired
    private UserRepresentationMapper mapper;

    @Test
    void mapEntities() {
        //given
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("user1@test.ru");
        request.setFirstName("firstname");
        request.setLastName("lastname");

        //when
        UserRepresentation userRepresentation = mapper.toUserRepresentation(request);

        //then
        assertNotNull(userRepresentation);
        assertEquals(request.getEmail(), userRepresentation.getEmail());
        assertEquals(request.getLastName(), userRepresentation.getLastName());
        assertEquals(request.getEmail(), userRepresentation.getUsername());
        assertEquals(request.getFirstName(), userRepresentation.getFirstName());
    }
}
