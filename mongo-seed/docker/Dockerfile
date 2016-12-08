FROM aleksandrbogomolov/mongodb

ADD init.sh /init.sh
ADD dump /dump

RUN chmod +x /init.sh
ENTRYPOINT ["/init.sh"]
