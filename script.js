async function name() {
  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwidXVpZCI6IjJmM2I0ZTllLWMzYzEtNDUyMi05YWJhLWFlOTE5NzJmYTlhNCIsInVzZXJuYW1lIjoieWFzc2luZSIsInN1YiI6IjIiLCJpYXQiOjE3NjI2ODU5MTcsImV4cCI6MTc2NTI3NzkxN30.VBUVLdZXe2x97kN1685j3m-AMyBL7BS2cjFasBYWaVE";
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  let form = new FormData();
  form.append("title", "sdfffffffffffffffffffffffhhsd");
  form.append(
    "content",
    "sdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsdsdfffffffffffffffffffffffhhsd"
  );
  let res = await fetch("http://localhost:8080/api/creat_post", {
    method: "POST",
    headers,
    body: form,
  });

  let data = await res.json();
  console.log(data);
}

setInterval(() => {
  name();
}, 1);
