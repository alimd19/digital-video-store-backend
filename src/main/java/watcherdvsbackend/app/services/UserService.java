package watcherdvsbackend.app.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import watcherdvsbackend.app.models.UserModel;
import watcherdvsbackend.app.models.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserModel getUserById(String id) throws Exception {
    Optional<UserModel> user = userRepository.findById(id);

    if (!user.isPresent()) {
      throw new Exception("No user found for the given Id: " + id);
    }

    return user.get();
  }

  public UserModel createUser(UserModel user) throws Exception {
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    try {
      UserModel exists = userRepository.findByUsername(user.getUsername());
      if (exists != null) {
        throw new Exception("Username already taken");
      }
      UserModel newUser = userRepository.insert(user);
      return newUser;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserModel foundUser = userRepository.findByUsername(username);

    if (foundUser == null) {
      throw new UsernameNotFoundException("User not found in the database");
    }

    String userN = foundUser.getUsername();
    String userP = foundUser.getPassword();

    return new User(userN, userP, new ArrayList<>());
  }
}
