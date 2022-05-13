package member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 저장소
    private static Map<Long, Member> store = new HashMap();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);  // hashmap에 (id, member) 쌍으로 저장

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // id(key)값으로 member 얻어오기
    }
}
