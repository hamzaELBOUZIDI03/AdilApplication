INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (1, 1.0, 'الكوفر عادل', 'الكوفر عادل', '#000', NULL)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (2, 1.0, 'السلف', 'السلف', '#fff', NULL)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (3, 1.0, 'الخليط', 'الخليط', '#ff0f', NULL)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO coffre_fort (id, coffre_total, coffre_name, coffre_name_ihm, coffre_color, date_derniere_modification)
VALUES (4, 1.0, 'الكوفر لكبير', 'الكوفر لكبير', '#ff01', NULL)
    ON CONFLICT (id) DO NOTHING;
