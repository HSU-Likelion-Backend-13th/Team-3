package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // MemberServiceImpl 은 MemberRepository 인터페이스를 의존
    // MemberRepository 를 실제로 할당하는 부분이 구현체를 의존
    // -> 구체화와 추상화에 둘다 의존하고 있음
    // 구현체 변경 시 코드 변경 필요 -> OCP 위반
    // 인터페이스에 의존했지만, 실제 할당은 구현체를 -> DIP 위반
    private final MemberRepository memberRepository;

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
