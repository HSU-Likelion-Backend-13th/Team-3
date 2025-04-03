package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    // id를 기준으로 member를 저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    // 반환형이 Member니까 store에서 가져온다.
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
