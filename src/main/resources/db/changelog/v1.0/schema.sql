create table employees (
    id serial primary key,
    name text not null,
    account_id int
);
GO

create table accounts(
    id serial primary key,
    login text not null,
    password text not null
);
GO

alter table employees
add constraint FK_employees_accounts foreign key(account_id)
    references accounts(id);