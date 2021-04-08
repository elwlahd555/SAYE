-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- user Table Create SQL
CREATE TABLE user
(
    `u_no`         INT              NOT NULL    AUTO_INCREMENT COMMENT '유저 번호', 
    `u_id`         VARCHAR(300)     NOT NULL    COMMENT '유저 아이디(UK)', 
    `u_password`   VARCHAR(3000)    NOT NULL    COMMENT '유저 비밀번호', 
    `u_nickname`   VARCHAR(300)     NULL        COMMENT '유저 닉네임(UK)', 
    `u_img`        VARCHAR(3000)    NULL        COMMENT '유저 이미지', 
    `u_genre`      VARCHAR(3000)    NULL        COMMENT '유저 장르', 
    `u_joindate`   TIMESTAMP        NOT NULL    COMMENT '유저 가입일', 
    `u_provider`   VARCHAR(300)     NULL        COMMENT '유저 SNS 연동 제공자', 
    `u_authority`  VARCHAR(300)     NOT NULL    COMMENT '유저 권한', 
    `u_salt`       VARCHAR(3000)    NULL        COMMENT '유저 고유 salt', 
    `u_jwt`        VARCHAR(3000)    NULL        COMMENT '유저 토큰', 
    PRIMARY KEY (u_no)
);


-- user Table Create SQL
CREATE TABLE music
(
    `m_no`          INT             NOT NULL    AUTO_INCREMENT COMMENT '음악 번호', 
    `m_title`       TEXT            NOT NULL    COMMENT '제목', 
    `m_genre`       TEXT            NOT NULL    COMMENT '장르', 
    `m_artist`      TEXT            NOT NULL    COMMENT '가수', 
    `m_album`       TEXT            NOT NULL    COMMENT '앨범', 
    `m_preview`     TEXT            NULL        COMMENT '미리듣기', 
    `m_img`         TEXT            NULL        COMMENT '이미지', 
    `m_popularity`  TEXT            NULL        COMMENT '인기도', 
    `m_id`          VARCHAR(200)    NOT NULL    COMMENT '음악ID', 
    `m_artist_id`   TEXT            NOT NULL    COMMENT '가수ID', 
    `m_album_id`    TEXT            NOT NULL    COMMENT '앨범ID', 
    `m_cnt`         INT             NULL        COMMENT '추천횟수', 
    `m_emotion`     TEXT            NULL        COMMENT '감정', 
    `m_date`        TEXT            NULL        COMMENT '발매일', 
    `m_url`         TEXT            NULL        COMMENT '유튜브URL', 
    PRIMARY KEY (m_no)
);


-- user Table Create SQL
CREATE TABLE playlist
(
    `p_no`    INT              NOT NULL    AUTO_INCREMENT COMMENT '플레이리스트고유번호', 
    `p_u_no`  INT              NULL        COMMENT '사용자고유번호', 
    `p_name`  VARCHAR(3000)    NULL        COMMENT '플레이리스트명', 
    PRIMARY KEY (p_no)
);

ALTER TABLE playlist COMMENT '내 플레이리스트';

ALTER TABLE playlist
    ADD CONSTRAINT FK_playlist_p_u_no_user_u_no FOREIGN KEY (p_u_no)
        REFERENCES user (u_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE board
(
    `b_no`       INT              NOT NULL    AUTO_INCREMENT COMMENT '게시판 고유 번호', 
    `b_class`    VARCHAR(300)     NOT NULL    COMMENT '게시판 글분류', 
    `b_title`    VARCHAR(3000)    NOT NULL    COMMENT '게시판 타이틀', 
    `b_content`  VARCHAR(3000)    NOT NULL    COMMENT '게시판 내용', 
    `b_writer`   VARCHAR(300)     NOT NULL    COMMENT '게시판 익명이름', 
    `b_date`     DATETIME         NOT NULL    COMMENT '게시판 작성일자', 
    `b_u_no`     INT              NOT NULL    COMMENT '작성자 고유번호', 
    PRIMARY KEY (b_no)
);

ALTER TABLE board COMMENT '익명게시판';

ALTER TABLE board
    ADD CONSTRAINT FK_board_b_u_no_user_u_no FOREIGN KEY (b_u_no)
        REFERENCES user (u_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE music_comment
(
    `mc_no`       INT              NOT NULL    AUTO_INCREMENT COMMENT '댓글 고유 번호', 
    `mc_u_no`     INT              NOT NULL    COMMENT '댓글 작성자 번호', 
    `mc_m_no`     INT              NOT NULL    COMMENT '연관된 음악 번호', 
    `mc_comment`  VARCHAR(3000)    NOT NULL    COMMENT '댓글 내용', 
    PRIMARY KEY (mc_no)
);

ALTER TABLE music_comment COMMENT '해당 음악을 들었을 때, 사용자들 댓글(유튜브 댓글생각하면됌)';

ALTER TABLE music_comment
    ADD CONSTRAINT FK_music_comment_mc_u_no_user_u_no FOREIGN KEY (mc_u_no)
        REFERENCES user (u_no) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE music_comment
    ADD CONSTRAINT FK_music_comment_mc_m_no_music_m_no FOREIGN KEY (mc_m_no)
        REFERENCES music (m_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE like_music
(
    `lm_u_no`  INT    NOT NULL    COMMENT '사용자고유번호', 
    `lm_m_no`  INT    NOT NULL    COMMENT '사용자가찜누른노래', 
    PRIMARY KEY (lm_u_no, lm_m_no)
);

ALTER TABLE like_music
    ADD CONSTRAINT FK_like_music_lm_m_no_music_m_no FOREIGN KEY (lm_m_no)
        REFERENCES music (m_no) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE like_music
    ADD CONSTRAINT FK_like_music_lm_u_no_user_u_no FOREIGN KEY (lm_u_no)
        REFERENCES user (u_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE playlist_music
(
    `pm_p_no`  INT    NOT NULL    COMMENT '플레이리스트고유번호', 
    `pm_m_no`  INT    NULL        COMMENT '노래고유번호'
);

ALTER TABLE playlist_music COMMENT '플레이리스트 내 노래';

ALTER TABLE playlist_music
    ADD CONSTRAINT FK_playlist_music_pm_p_no_playlist_p_no FOREIGN KEY (pm_p_no)
        REFERENCES playlist (p_no) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE playlist_music
    ADD CONSTRAINT FK_playlist_music_pm_m_no_music_m_no FOREIGN KEY (pm_m_no)
        REFERENCES music (m_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE comment
(
    `c_no`       INT              NOT NULL    AUTO_INCREMENT COMMENT '댓글 고유 번호', 
    `c_b_no`     INT              NOT NULL    COMMENT '댓글의 게시판 번호', 
    `c_writer`   VARCHAR(300)     NOT NULL    COMMENT '댓글 작성자', 
    `c_content`  VARCHAR(3000)    NOT NULL    COMMENT '댓글 내용', 
    `c_date`     DATETIME         NOT NULL    COMMENT '댓글 작성일자', 
    `c_u_no`     INT              NOT NULL    COMMENT '작성자 고유번호', 
    PRIMARY KEY (c_no)
);

ALTER TABLE comment COMMENT '게시판 댓글';

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_c_b_no_board_b_no FOREIGN KEY (c_b_no)
        REFERENCES board (b_no) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE comment
    ADD CONSTRAINT FK_comment_c_u_no_user_u_no FOREIGN KEY (c_u_no)
        REFERENCES user (u_no) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- user Table Create SQL
CREATE TABLE audio_transition_data
(
    `a_no`                     int             NULL        DEFAULT NULL COMMENT '오디오 번호', 
    `artist`                   text            NULL        COMMENT '가수', 
    `title`                    text            NULL        COMMENT '제목', 
    `genre`                    text            NULL        COMMENT '장르', 
    `url`                      text            NULL        COMMENT '미리듣기', 
    `id`                       VARCHAR(200)    NULL        DEFAULT NULL COMMENT '음악ID', 
    `chroma_stft_mean`         double          NULL        DEFAULT NULL, 
    `chroma_stft_var`          double          NULL        DEFAULT NULL, 
    `rms_mean`                 double          NULL        DEFAULT NULL, 
    `rms_var`                  double          NULL        DEFAULT NULL, 
    `spectral_centroid_mean`   double          NULL        DEFAULT NULL, 
    `spectral_centroid_var`    double          NULL        DEFAULT NULL, 
    `spectral_bandwidth_mean`  double          NULL        DEFAULT NULL, 
    `spectral_bandwidth_var`   double          NULL        DEFAULT NULL, 
    `rolloff_mean`             double          NULL        DEFAULT NULL, 
    `rolloff_var`              double          NULL        DEFAULT NULL, 
    `zero_crossing_rate_mean`  double          NULL        DEFAULT NULL, 
    `zero_crossing_rate_var`   double          NULL        DEFAULT NULL, 
    `harmony_mean`             double          NULL        DEFAULT NULL, 
    `harmony_var`              double          NULL        DEFAULT NULL, 
    `perceptr_mean`            double          NULL        DEFAULT NULL, 
    `perceptr_var`             double          NULL        DEFAULT NULL, 
    `tempo`                    double          NULL        DEFAULT NULL, 
    `mfcc1_mean`               double          NULL        DEFAULT NULL, 
    `mfcc1_var`                double          NULL        DEFAULT NULL, 
    `mfcc2_mean`               double          NULL        DEFAULT NULL, 
    `mfcc2_var`                double          NULL        DEFAULT NULL, 
    `mfcc3_mean`               double          NULL        DEFAULT NULL, 
    `mfcc3_var`                double          NULL        DEFAULT NULL, 
    `mfcc4_mean`               double          NULL        DEFAULT NULL, 
    `mfcc4_var`                double          NULL        DEFAULT NULL, 
    `mfcc5_mean`               double          NULL        DEFAULT NULL, 
    `mfcc5_var`                double          NULL        DEFAULT NULL, 
    `mfcc6_mean`               double          NULL        DEFAULT NULL, 
    `mfcc6_var`                double          NULL        DEFAULT NULL, 
    `mfcc7_mean`               double          NULL        DEFAULT NULL, 
    `mfcc7_var`                double          NULL        DEFAULT NULL, 
    `mfcc8_mean`               double          NULL        DEFAULT NULL, 
    `mfcc8_var`                double          NULL        DEFAULT NULL, 
    `mfcc9_mean`               double          NULL        DEFAULT NULL, 
    `mfcc9_var`                double          NULL        DEFAULT NULL, 
    `mfcc10_mean`              double          NULL        DEFAULT NULL, 
    `mfcc10_var`               double          NULL        DEFAULT NULL, 
    `mfcc11_mean`              double          NULL        DEFAULT NULL, 
    `mfcc11_var`               double          NULL        DEFAULT NULL, 
    `mfcc12_mean`              double          NULL        DEFAULT NULL, 
    `mfcc12_var`               double          NULL        DEFAULT NULL, 
    `mfcc13_mean`              double          NULL        DEFAULT NULL, 
    `mfcc13_var`               double          NULL        DEFAULT NULL, 
    `mfcc14_mean`              double          NULL        DEFAULT NULL, 
    `mfcc14_var`               double          NULL        DEFAULT NULL, 
    `mfcc15_mean`              double          NULL        DEFAULT NULL, 
    `mfcc15_var`               double          NULL        DEFAULT NULL, 
    `mfcc16_mean`              double          NULL        DEFAULT NULL, 
    `mfcc16_var`               double          NULL        DEFAULT NULL, 
    `mfcc17_mean`              double          NULL        DEFAULT NULL, 
    `mfcc17_var`               double          NULL        DEFAULT NULL, 
    `mfcc18_mean`              double          NULL        DEFAULT NULL, 
    `mfcc18_var`               double          NULL        DEFAULT NULL, 
    `mfcc19_mean`              double          NULL        DEFAULT NULL, 
    `mfcc19_var`               double          NULL        DEFAULT NULL, 
    `mfcc20_mean`              double          NULL        DEFAULT NULL, 
    `mfcc20_var`               double          NULL        DEFAULT NULL
);

ALTER TABLE audio_transition_data
    ADD CONSTRAINT FK_audio_transition_data_id_music_m_id FOREIGN KEY (id)
        REFERENCES music (m_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE audio_transition_data
    ADD CONSTRAINT UC_audio_transition_data_id UNIQUE (id);


