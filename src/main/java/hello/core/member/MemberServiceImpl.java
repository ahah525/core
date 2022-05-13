package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // MemberServiceImpl 은 MemberRepository 인터페이스에만 의존
    private final MemberRepository memberRepository;

    // 의존성 주입(DI)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
