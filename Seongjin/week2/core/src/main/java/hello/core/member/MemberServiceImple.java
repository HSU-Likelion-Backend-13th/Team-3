package hello.core.member;

public class MemberServiceImple implements MemberService {
    //MemberServiceImple은 MemberRepositoryy 인터페이스를 의존
    // MemberRepository를 실제로 할당하는 부분이 구현체를 의존
    // -> 구체화와 추상화에 둘다 의존하고 있음
    // -> OCP, DIP 모두 위반.
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
