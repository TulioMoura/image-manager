CREATE TABLE imageDescriptor(uuid TEXT(36) NOT NULL,
uploadDate INTEGER NOT NULL,
characteristics TEXT(256) NOT NULL,
CONSTRAINT imageDescriptor_pk PRIMARY KEY (uuid));
CREATE TABLE galleries (
	nome TEXT(64),
	id TEXT(36),
	created_at INTEGER,
	CONSTRAINT galleries_PK PRIMARY KEY (id)
);
CREATE TABLE categories (
	nome TEXT,
	inclusao INTEGER, Column1 TEXT(36),
	CONSTRAINT categories_PK PRIMARY KEY (nome)
);
CREATE TABLE imageGallery (
	image_id TEXT(36),
	gallery_id text(36),
	CONSTRAINT imageGallery_PK PRIMARY KEY (image_id,gallery_id),
	CONSTRAINT imageGallery_FK FOREIGN KEY (image_id) REFERENCES imageDescriptor(uuid),
	CONSTRAINT imageGallery_FK_1 FOREIGN KEY (gallery_id) REFERENCES galleries(id)
);
CREATE TABLE imageCategories (
	category TEXT(36),
	image_id TEXT(36),
	CONSTRAINT imageCategories_PK PRIMARY KEY (category,image_id),
	CONSTRAINT imageCategories_FK FOREIGN KEY (image_id) REFERENCES imageDescriptor(uuid),
	CONSTRAINT imageCategories_FK_1 FOREIGN KEY (category) REFERENCES categories(nome)
);

