package hello.core.member;

public interface MemberRepository {
    // 새로운 회원을 DB에 저장
    void save(Member member);

    // memberID로 DB에서 회원 조회
    Member findById(Long id);
}
