package com.lufegaba.bnb.infraestructure.services;

import com.lufegaba.bnb.domain.*;
import com.lufegaba.bnb.domain.repositories.AddressRepository;
import com.lufegaba.bnb.domain.repositories.PhoneRepository;
import com.lufegaba.bnb.domain.repositories.RoleGroupRepository;
import com.lufegaba.bnb.domain.repositories.UserRepository;
import com.lufegaba.bnb.infraestructure.exceptions.IdNotFoundException;
import com.lufegaba.bnb.infraestructure.exceptions.RoleExistsException;
import com.lufegaba.bnb.infraestructure.utilities.Tables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    private final RoleGroupRepository roleGroupRepository;

    public User createUser (User user) {
        var roleToAdd = new RoleGroup(user, Role.LODGER);
        var roles = new HashSet<RoleGroup>();
        var userSaved = userRepository.save(user);
        var now = LocalDateTime.now();
        roleGroupRepository.save(roleToAdd);
        roles.add(roleToAdd);
        user.setRoles(roles);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return userSaved;
    }

    public List<User> showAllUsers () {
        return userRepository.findAll();
    }

    public User getUserById (Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(Tables.users.name()));
    }

    public User addAddress (Integer id, Address address) {
        var userToUpdate = getUserById(id);
        addressRepository.save(address);
        userToUpdate.setAddress(address);
        return userToUpdate;
    }

    public User addPhone (Integer id, Phone phone) {
        var userToUpdate = getUserById(id);
        phoneRepository.save(phone);
        userToUpdate.setPhone(phone);
        return userToUpdate;
    }

    public User addRole (Integer id, Role role) {
        var userToUpdate = getUserById(id);
        var roles = userToUpdate.getRoles();
        var roleGroupToAdd = roleGroupRepository.save(new RoleGroup(userToUpdate, role));
        roles.forEach(roleGroup -> {
            if (roleGroup.getRole().equals(roleGroupToAdd.getRole())){
                throw new RoleExistsException();
            }
        });
        roles.add(roleGroupToAdd);
        userToUpdate.setRoles(roles);
        return userRepository.save(userToUpdate);
    }

    public User updateUser (Integer id, User user) {
        var userToUpdate = getUserById(id);
        if (user.getEmail() != null) userToUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null) userToUpdate.setPassword(user.getPassword());
        if (user.getAddress() != null) addAddress(id, user.getAddress());
        if (user.getPhone() != null) addPhone(id, user.getPhone());
        if (user.getRoles() != null) addRole(id, user.getRoles().stream().findFirst().orElseThrow().getRole());
        userToUpdate.setUpdatedAt(LocalDateTime.now());
        return userToUpdate;
    }

    public void deleteUserById (Integer id) {
        userRepository.deleteById(id);
    }
    

}
