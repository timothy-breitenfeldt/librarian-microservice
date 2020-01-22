@echo off

set contenttype="Content-Type: application/xml"
set url=http://localhost:8081/lms/librarian/books

::set data="{\"title\": \"I am tired \", \"author\": \"This author was updated also\"}"
:: curl -X DELETE -d %data% -H %contenttype% %url%
curl -H %contenttype% %url%