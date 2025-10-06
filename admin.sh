#!/bin/bash
CONTAINER_NAME="01blogdb"
DB_NAME="blogbd"
DB_USER="ysnbhb"

read -p "Enter admin email: " email
read -p "Enter admin username: " username
read -p "Enter admin first name: " first_name
read -p "Enter admin last name: " last_name
read -sp "Enter admin password: " password
echo ""

hashed_password=$(echo "$password" | openssl passwd -6 -stdin)

dob="----------"
url_photo="default-avatar.jpg"
role="ADMIN"

docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME <<EOF
INSERT INTO users (email, username, "name", "lastName", password, role, "dateOfBirth", "urlPhoto")
VALUES ('$email', '$username', '$first_name', '$last_name', '$hashed_password', '$role', '$dob', '$url_photo');
EOF

echo "Admin inserted successfully!"
