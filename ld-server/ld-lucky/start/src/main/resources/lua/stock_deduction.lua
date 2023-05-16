local key = KEYS[1]
local usedstore = tonumber(redis.call('get', key))
if usedstore ~= nil and usedstore > 0 then
    local current = tonumber(redis.call('decr', key))
    return current
end
--若此时库存为0,则直接返回-1,不写redis;在库存不足时减小写压力(1次扣减 + 1次回滚)
if usedstore ~= nil and usedstore == 0 then
    return -1
end
return usedstore
