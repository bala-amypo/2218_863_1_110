public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    // ✅ REQUIRED BY TESTS
    User findByEmail(String email);
}
