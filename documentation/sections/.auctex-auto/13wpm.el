(TeX-add-style-hook
 "13wpm"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("subfiles" "../informe.tex")))
   (TeX-run-style-hooks
    "latex2e"
    "subfiles"
    "subfiles10")
   (LaTeX-add-labels
    "subsection:hard_clauses"))
 :latex)

