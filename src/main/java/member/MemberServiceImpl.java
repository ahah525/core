package member;

public class MemberServiceImpl implements MemberService{
    
    // 가입과 조회를 하기 위해 MemberRepository의 구현체를 생성
    // MemberServiceImpl은 MemberRepository, MemoryMemberRepository 모두 의존(DIP 위반)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
