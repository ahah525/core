package member;

public interface MemberService {
    //회원 가입 메서드
    void join(Member member);

    // 회원 조회 메서드
    Member findMember(Long memberId);
}
