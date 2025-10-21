SELECT
    I_CHILD.ITEM_ID,
    I_CHILD.ITEM_NAME,
    I_CHILD.RARITY
FROM
    ITEM_INFO AS I_PARENT  -- 1. 부모 아이템의 정보를 위한 테이블
JOIN
    ITEM_TREE AS T         -- 2. 부모-자식 관계를 연결하기 위한 테이블
ON
    I_PARENT.ITEM_ID = T.PARENT_ITEM_ID
JOIN
    ITEM_INFO AS I_CHILD   -- 3. 자식 아이템의 정보를 위한 테이블
ON
    T.ITEM_ID = I_CHILD.ITEM_ID
WHERE
    I_PARENT.RARITY = 'RARE' -- 4. 부모의 희귀도가 'RARE'인 조건
ORDER BY
    I_CHILD.ITEM_ID DESC;    -- 5. 자식 아이템 ID 기준 내림차순 정렬