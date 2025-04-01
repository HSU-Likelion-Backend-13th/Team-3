package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImple();

        Member member = new Member(1L,"Lion",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember :" + findMember.getName());
        System.out.println("Member :" + member.getName());
    }
}
