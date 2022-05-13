package hello.core.member;

public interface MemberRepository {

    // 회원 저장 메서드
    void save(Member member);

    // 회원의 id로 회원을 조회하는 메서드
    Member findById(Long memberId);
}
