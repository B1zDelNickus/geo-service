var db = connect("mongodb://test:test@localhost:27017/admin");

db = db.getSiblingDB('testdb'); // we can not use "use" statement here to switch db

db.createUser(
    {
        user: "user",
        pwd: "pass",
        roles: [ { role: "readWrite", db: "testdb"} ],
        passwordDigestor: "server",
    }
)