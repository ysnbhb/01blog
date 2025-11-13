async function name() {
  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MywidXVpZCI6ImNhYmMwNzQ5LTEzMTEtNGQ4MS1hNDU1LTQ3ZTdmNjNmZDJlNyIsInVzZXJuYW1lIjoieWFzc2luZSIsInN1YiI6IjMiLCJpYXQiOjE3NjMwNDA3MzUsImV4cCI6MTc2NTYzMjczNX0.ED-CMZYUCZ6pKMLkYDflAXm3sJdaO4HBAZ5qsp3SNl8";
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
