insert into client (name)
select upper(substr(raw, 1, 1)) || lower(substr(raw, 2, len))
from (select regexp_replace(uuid() || uuid(), '[^a-zA-Z]', '') as raw,
             3 + mod(x, 98)                                    as len
      from system_range(1, 500)) t;

insert into planet (id, name)
select upper(substr(replace(uuid(), '-', ''), 1, 8))          as id,
       upper(substr(raw, 1, 1)) || lower(substr(raw, 2, len)) as name
from (select regexp_replace(uuid() || uuid(), '[^a-zA-Z]', '') as raw,
             1 + mod(x, 300)                                   as len
      from system_range(1, 30)) t;

insert into ticket (created_at, client_id, from_planet_id, to_planet_id)
select dateadd('SECOND', x, current_timestamp at time zone 'UTC'),
       (select id from client order by rand() limit 1),
    p1.id,
    p2.id
from system_range(1, 500)
    join planet p1
on true
    join planet p2 on true
where p1.id <> p2.id
order by rand()
    limit 500;