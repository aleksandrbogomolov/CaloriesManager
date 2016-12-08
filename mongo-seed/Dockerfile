FROM aleksandrbogomolov/mongodb

ADD docker_init.sh /init.sh
ADD populate.js /populate.js

RUN chmod +x /init.sh
ENTRYPOINT ["/init.sh"]
