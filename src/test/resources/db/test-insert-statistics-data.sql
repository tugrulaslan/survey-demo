insert into polls (id) values (1);

insert into poll_questions(id, question, poll_id) values (1, 'what is the most popular question on earth', 1);

insert into QUESTION_CHOICES (id, choice, poll_question_id) values (1, 'java', 1);
insert into QUESTION_CHOICES (id, choice, poll_question_id) values (2, 'python', 1);
insert into QUESTION_CHOICES (id, choice, poll_question_id) values (3, '.net', 1);
insert into QUESTION_CHOICES (id, choice, poll_question_id) values (4, 'rust', 1);
insert into QUESTION_CHOICES (id, choice, poll_question_id) values (5, 'go', 1);
insert into QUESTION_CHOICES (id, choice, poll_question_id) values (6, 'kotlin', 1);

insert into responses (id, poll_question_id) values (1, 1);
insert into response_choices (response_id, choice_id) values (1, 1);
insert into response_choices (response_id, choice_id) values (1, 6);

insert into responses (id, poll_question_id) values (2, 1);
insert into response_choices (response_id, choice_id) values (2, 3);

insert into responses (id, poll_question_id) values (3, 1);
insert into response_choices (response_id, choice_id) values (3, 2);
insert into response_choices (response_id, choice_id) values (3, 4);
insert into response_choices (response_id, choice_id) values (3, 5);

insert into responses (id, poll_question_id) values (4, 1);
insert into response_choices (response_id, choice_id) values (4, 1);
insert into response_choices (response_id, choice_id) values (4, 5);

insert into responses (id, poll_question_id) values (5, 1);
insert into response_choices (response_id, choice_id) values (5, 4);
insert into response_choices (response_id, choice_id) values (5, 5);

insert into responses (id, poll_question_id) values (6, 1);
insert into response_choices (response_id, choice_id) values (6, 1);

insert into responses (id, poll_question_id) values (7, 1);
insert into response_choices (response_id, choice_id) values (7, 1);
insert into response_choices (response_id, choice_id) values (7, 2);
insert into response_choices (response_id, choice_id) values (7, 5);

insert into responses (id, poll_question_id) values (8, 1);
insert into response_choices (response_id, choice_id) values (8, 4);
insert into response_choices (response_id, choice_id) values (8, 5);
insert into response_choices (response_id, choice_id) values (8, 6);