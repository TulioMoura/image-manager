CREATE TABLE img_descriptor(uuid varchar(36) NOT NULL,
uploadDate BIGINT NOT NULL,
characteristics varchar(256) NOT NULL,
CONSTRAINT img_descriptor_pk PRIMARY KEY (uuid));
CREATE TABLE gallery (
	name varchar(64) UNIQUE,
	id varchar(36),
	created_at BIGINT,
	CONSTRAINT gallery_PK PRIMARY KEY (id)
);
CREATE TABLE category (
	name varchar(36) UNIQUE,
	created_at BIGINT,
	CONSTRAINT category_PK PRIMARY KEY (name)
);
CREATE TABLE image_gallery (
	image_id varchar(36) ,
	gallery_id varchar(36),
	CONSTRAINT ImageGallery_PK PRIMARY KEY (image_id,gallery_id),
	CONSTRAINT ImageGallery_FK FOREIGN KEY (image_id) REFERENCES img_descriptor(uuid),
	CONSTRAINT ImageGallery_FK_1 FOREIGN KEY (gallery_id) REFERENCES gallery(id)
);
CREATE TABLE image_category (
	category_name varchar(36),
	image_id varchar(36),
	CONSTRAINT imageCategories_PK PRIMARY KEY (category_name,image_id),
	CONSTRAINT imageCategories_FK FOREIGN KEY (image_id) REFERENCES img_descriptor(uuid),
	CONSTRAINT imageCategories_FK_1 FOREIGN KEY (category_name) REFERENCES category(name)
);


