local key = KEYS[1]
local usedstore = tonumber(redis.call('get', key))
if usedstore ~= nil and usedstore > -1 then
    local current = tonumber(redis.call('incr', key))
    return current
end
return usedstore