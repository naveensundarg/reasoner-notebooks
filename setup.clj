
(import com.naveensundarg.shadow.prover.representations.formula.Formula)
(import com.naveensundarg.shadow.prover.utils.Reader)
(import java.util.HashSet)


(def ccprover (new com.naveensundarg.shadow.prover.core.ccprovers.CognitiveCalculusProver))


(def prover ())
(defn formulae [assumptions]
    (map (fn [key] (.setJustification (Reader/readFormulaFromString (print-str (assumptions key)))
                                     (new com.naveensundarg.shadow.prover.core.proof.AtomicJustification (print-str key) ))) 
         (keys assumptions)))
(defn reason [{assumptions :assumptions goal :goal}] 
 (let [ans (.prove ccprover (set (formulae assumptions)) (Reader/readFormulaFromString (print-str goal)))]
     (if (.isPresent ans)
         (.get ans)
         "NO PROOF FOUND")))   

(def tmp (reason {:assumptions '{:A1  P :A2 Q}  :goal 'Q}))
 