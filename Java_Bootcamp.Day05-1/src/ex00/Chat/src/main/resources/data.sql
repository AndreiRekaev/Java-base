INSERT INTO Chat.User(login, password) VALUES
    ('user1', '1111'),
    ('user2', '2222'),
    ('user3' , '3333'),
    ('user4', '4444'),
    ('user5', '5555'),
    ('user6', '6666');

INSERT INTO Chat.chatroom(chatroomname, chatroomowner) VALUES
    (1, (SELECT UserId FROM chat.User WHERE login = 'user1')),
    (2, (SELECT UserId FROM chat.User WHERE login = 'user2')),
    (3, (SELECT UserId FROM chat.User WHERE login = 'user3')),
    (4, (SELECT UserId FROM chat.User WHERE login = 'user4'));

INSERT INTO Chat.message(messageauthor, messageroom, messagetext) VALUES
    ((SELECT UserId FROM chat.user WHERE login = 'user1'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'Hello world!'),
    ((SELECT UserId FROM chat.user WHERE login = 'user2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'Hi, user1!'),
    ((SELECT UserId FROM chat.user WHERE login = 'user3'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1'), 'Hello, my friends!'),
    ((SELECT UserId FROM chat.user WHERE login = 'user4'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'Привет!'),
    ((SELECT UserId FROM chat.user WHERE login = 'user2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'ХА-ХА-ХА'),
    ((SELECT UserId FROM chat.user WHERE login = 'user3'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'), 'Bye bye');

INSERT INTO Chat.user_chatrooms(userid, chatroomid) VALUES
    ((SELECT UserId FROM chat.user WHERE login = 'user1'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'user3'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'user3'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '3')),
    ((SELECT UserId FROM chat.user WHERE login = 'user2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '1')),
    ((SELECT UserId FROM chat.user WHERE login = 'user2'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '2')),
    ((SELECT UserId FROM chat.user WHERE login = 'user4'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4')),
    ((SELECT UserId FROM chat.user WHERE login = 'user5'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4')),
    ((SELECT UserId FROM chat.user WHERE login = 'user6'),
     (SELECT ChatRoomId FROM chat.chatroom WHERE chatroomname = '4'));