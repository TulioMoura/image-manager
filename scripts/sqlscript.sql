CREATE TABLE img_descriptor(uuid TEXT(36) NOT NULL,
uploadDate INTEGER NOT NULL,
characteristics TEXT(256) NOT NULL,
CONSTRAINT img_descriptor_pk PRIMARY KEY (uuid));
CREATE TABLE gallery (
	name TEXT(64),
	id TEXT(36),
	created_at INTEGER,
	CONSTRAINT gallery_PK PRIMARY KEY (id)
);
CREATE TABLE category (
	nome TEXT,
	created_at INTEGER,
	CONSTRAINT category_PK PRIMARY KEY (nome)
);
CREATE TABLE image_gallery (
	image_id TEXT(36),
	gallery_id text(36),
	CONSTRAINT ImageGallery_PK PRIMARY KEY (image_id,gallery_id),
	CONSTRAINT ImageGallery_FK FOREIGN KEY (image_id) REFERENCES img_descriptor(uuid),
	CONSTRAINT ImageGallery_FK_1 FOREIGN KEY (gallery_id) REFERENCES gallery(id)
);
CREATE TABLE image_categories (
	category_name TEXT(36),
	image_id TEXT(36),
	CONSTRAINT imageCategories_PK PRIMARY KEY (category_name,image_id),
	CONSTRAINT imageCategories_FK FOREIGN KEY (image_id) REFERENCES img_descriptor(uuid),
	CONSTRAINT imageCategories_FK_1 FOREIGN KEY (category_name) REFERENCES category(nome)
);

