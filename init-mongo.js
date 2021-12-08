db.createUser({
  user: "grupo51",
  pwd: "grupo51",
  roles: [ { role: "readWrite", db: "campaignsDB" } ]
})
